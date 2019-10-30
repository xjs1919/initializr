/** 
 * date: 2019/10/29 19:20
 * copyright(c) 2019-2029 mamcharge.com
 */
 
package io.spring.initializr.generator.buildsystem.maven;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * a profile of a {@link MavenBuild}.
 *
 * @author JoshuaXu
 **/
public class MavenProfile {

    private String id;
    private boolean activeByDefault;
    private List<Property> properties;

    public MavenProfile(MavenProfile.Builder builder){
        this.id = builder.id;
        this.activeByDefault = builder.activeByDefault;
        this.properties = builder.getPropertiesBuilder().getProperties();
    }

    public String getId() {
        return id;
    }

    public boolean isActiveByDefault() {
        return activeByDefault;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public static class Builder{

        private String id;
        private boolean activeByDefault;
        private PropertiesBuilder propertiesBuilder;

        public Builder(String id, boolean activateByDefault){
            this.id = id;
            this.activeByDefault = activateByDefault;
        }

        public MavenProfile build(){
            return new MavenProfile(this);
        }

        public Builder cofiguration(Consumer<PropertiesBuilder> propertiesBuilder){
            if(this.propertiesBuilder == null){
                this.propertiesBuilder = new PropertiesBuilder();
            }
            propertiesBuilder.accept(this.propertiesBuilder);
            return this;
        }

        public PropertiesBuilder getPropertiesBuilder(){
            return this.propertiesBuilder;
        }

        public boolean activeByDefault(){
            return this.activeByDefault;
        }

        public static class PropertiesBuilder{
            private List<Property> properties;
            public void add(String propName, String propValue) {
                if(this.properties == null){
                    this.properties = new ArrayList<>();
                }
                this.properties.add(new Property(propName,propValue));
            }
            public List<Property> getProperties(){
                return this.properties;
            }
        }
    }

    public static class Property{
        private String name;
        private String value;
        public Property(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
