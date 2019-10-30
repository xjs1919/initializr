/** 
 * date: 2019/10/29 19:18
 * copyright(c) 2019-2029 mamcharge.com
 */
 
package io.spring.initializr.generator.buildsystem.maven;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MavenProfileContainer {

    private final Map<String, MavenProfile.Builder> profiles = new LinkedHashMap<>();

    public Map<String, MavenProfile.Builder> getProfiles(){
        return profiles;
    }

    public void add(String id, boolean activateByDefault, Consumer<MavenProfile.Builder> profileBuilder){
        profileBuilder.accept(createProfileBuilder(id, activateByDefault));
    }

    private MavenProfile.Builder createProfileBuilder(String id, boolean activateByDefault){
        MavenProfile.Builder profileBuilder =  this.profiles.get(id);
        if(profileBuilder == null){
            MavenProfile.Builder builder = new MavenProfile.Builder(id, activateByDefault);
            this.profiles.put(id, builder);
            return builder;
        }else{
            return profileBuilder;
        }
    }

}
