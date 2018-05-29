package com.microsoft.azure.springcloudplayground.util;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class VersionParser {

    /**
     * The default {@link VersionParser}.
     */
    public static final VersionParser DEFAULT = new VersionParser(
            Collections.emptyList());

    private static final Pattern VERSION_REGEX = Pattern
            .compile("^(\\d+)\\.(\\d+|x)\\.(\\d+|x)(?:\\.([^0-9]+)(\\d+)?)?$");

    private static final Pattern RANGE_REGEX = Pattern
            .compile("(\\(|\\[)(.*),(.*)(\\)|\\])");

    private final List<Version> latestVersions;

    public VersionParser(List<Version> latestVersions) {
        this.latestVersions = latestVersions;
    }

    /**
     * Parse the string representation of a {@link Version}. Throws an
     * {@link InvalidVersionException} if the version could not be parsed.
     * @param text the version text
     * @return a Version instance for the specified version text
     * @throws InvalidVersionException if the version text could not be parsed
     * @see #safeParse(java.lang.String)
     */
    public Version parse(String text) {
        Assert.notNull(text, "Text must not be null");
        Matcher matcher = VERSION_REGEX.matcher(text.trim());
        if (!matcher.matches()) {
            throw new InvalidVersionException(
                    "Could not determine version based on '" + text + "': version format "
                            + "is Minor.Major.Patch.Qualifier " + "(e.g. 1.0.5.RELEASE)");
        }
        Integer major = Integer.valueOf(matcher.group(1));
        String minor = matcher.group(2);
        String patch = matcher.group(3);
        Version.Qualifier qualifier = null;
        String qualifierId = matcher.group(4);
        if (StringUtils.hasText(qualifierId)) {
            qualifier = new Version.Qualifier(qualifierId);
            String o = matcher.group(5);
            if (o != null) {
                qualifier.setVersion(Integer.valueOf(o));
            }
        }
        if ("x".equals(minor) || "x".equals(patch)) {
            Integer minorInt = "x".equals(minor) ? null : Integer.parseInt(minor);
            Version latest = findLatestVersion(major, minorInt, qualifier);
            if (latest == null) {
                return new Version(major,
                        ("x".equals(minor) ? 999 : Integer.parseInt(minor)),
                        ("x".equals(patch) ? 999 : Integer.parseInt(patch)), qualifier);
            }
            return new Version(major, latest.getMinor(), latest.getPatch(),
                    latest.getQualifier());
        }
        else {
            return new Version(major, Integer.parseInt(minor), Integer.parseInt(patch),
                    qualifier);
        }
    }

    /**
     * Parse safely the specified string representation of a {@link Version}.
     * <p>
     * Return {@code null} if the text represents an invalid version.
     * @param text the version text
     * @return a Version instance for the specified version text
     * @see #parse(java.lang.String)
     */
    public Version safeParse(String text) {
        try {
            return parse(text);
        }
        catch (InvalidVersionException ex) {
            return null;
        }
    }

    /**
     * Parse the string representation of a {@link VersionRange}. Throws an
     * {@link InvalidVersionException} if the range could not be parsed.
     * @param text the range text
     * @return a VersionRange instance for the specified range text
     * @throws InvalidVersionException if the range text could not be parsed
     */
    public VersionRange parseRange(String text) {
        Assert.notNull(text, "Text must not be null");
        Matcher matcher = RANGE_REGEX.matcher(text.trim());
        if (!matcher.matches()) {
            // Try to read it as simple string
            Version version = parse(text);
            return new VersionRange(version, true, null, true);
        }
        boolean lowerInclusive = matcher.group(1).equals("[");
        Version lowerVersion = parse(matcher.group(2));
        Version higherVersion = parse(matcher.group(3));
        boolean higherInclusive = matcher.group(4).equals("]");
        return new VersionRange(lowerVersion, lowerInclusive, higherVersion,
                higherInclusive);
    }

    private Version findLatestVersion(Integer major, Integer minor,
                                      Version.Qualifier qualifier) {
        List<Version> matches = this.latestVersions.stream().filter((it) -> {
            if (major != null && !major.equals(it.getMajor())) {
                return false;
            }
            if (minor != null && !minor.equals(it.getMinor())) {
                return false;
            }
            if (qualifier != null && !qualifier.equals(it.getQualifier())) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());
        return (matches.size() == 1 ? matches.get(0) : null);
    }

}
