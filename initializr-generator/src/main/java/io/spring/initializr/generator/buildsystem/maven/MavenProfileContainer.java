/** 
 * date: 2019/10/29 19:18
 * copyright(c) 2019-2029 mamcharge.com
 */
 
package io.spring.initializr.generator.buildsystem.maven;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MavenProfileContainer {

    private final Map<String, MavenProfile> profiles = new LinkedHashMap<>();

    public MavenProfileContainer(){

        MavenProfile.Property property = null;

        property = new MavenProfile.Property("package.environment","dev");
        profiles.put("开发环境", new MavenProfile.Builder().property(property).activeByDefault(true).build());

        property = new MavenProfile.Property("package.environment","test");
        profiles.put("测试环境", new MavenProfile.Builder().property(property).activeByDefault(false).build());

        property = new MavenProfile.Property("package.environment","pre");
        profiles.put("预发环境", new MavenProfile.Builder().property(property).activeByDefault(false).build());

        property = new MavenProfile.Property("package.environment","pro");
        profiles.put("生产环境", new MavenProfile.Builder().property(property).activeByDefault(false).build());
    }

    public void add(String id, MavenProfile profile) {
        profiles.put(id, profile);
    }

    public Map<String, MavenProfile> getProfiles(){
        return this.profiles;
    }

}
