/** 
 * date: 2019/10/29 19:18
 * copyright(c) 2019-2029 mamcharge.com
 */
 
package io.spring.initializr.generator.buildsystem.maven;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * A container for {@link MavenProfile}s.
 *
 * @author JoshuaXu
 */
public class MavenProfileContainer {

    private final Map<String, MavenProfile.Builder> profiles = new LinkedHashMap<>();

    /**
     * Specify if this container is empty.
     * @return {@code true} if no {@link MavenProfile} is added
     */
    public boolean isEmpty() {
        return this.profiles.isEmpty();
    }

    /**
     * Returns a {@link Stream} of registered {@link MavenProfile}s.
     * @return a stream of {@link MavenProfile}s
     */
    public Stream<MavenProfile> values() {
        return this.profiles.values().stream().map(MavenProfile.Builder::build);
    }

    /**
     * Add a {@link MavenProfile} with the specified {@code id} and {@code activateByDefault}
     * and {@link MavenProfile.Builder} to customize the profile. If the profile has already been added,
     * the profileBuilder can be used to further tune the existing profile configuration.
     * @param id the id of the profile
     * @param activateByDefault the activateByDefault of the profile
     * @param profileBuilder a {@link MavenProfile.Builder} to customize the {@link MavenProfile}
     */
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
