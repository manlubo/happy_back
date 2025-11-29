package com.gitbaby.happy_back.domain.common.util;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.springframework.stereotype.Component;

@Component
public class VersionUtil {
    // 버전 파싱
    public DefaultArtifactVersion parse(String version) {
        return new DefaultArtifactVersion(version);
    }

    // 최신 버전 비교
    public int compare(String v1, String v2) {
        return parse(v1).compareTo(parse(v2));
    }

    // 메이저 증가 (1.0.0 → 2.0.0)
    public String nextMajor(String version) {
        DefaultArtifactVersion v = parse(version);
        int major = v.getMajorVersion() + 1;
        return major + ".0.0";
    }

    // 마이너 증가 (1.0.0 → 1.1.0)
    public String nextMinor(String version) {
        DefaultArtifactVersion v = parse(version);
        int major = v.getMajorVersion();
        int minor = v.getMinorVersion() + 1;
        return major + "." + minor + ".0";
    }

    // 패치 증가 (1.0.0 → 1.0.1)
    public String nextPatch(String version) {
        DefaultArtifactVersion v = parse(version);
        int major = v.getMajorVersion();
        int minor = v.getMinorVersion();
        int patch = v.getIncrementalVersion() + 1;
        return major + "." + minor + "." + patch;
    }
}
