/** 
 * date: 2019/10/29 19:20
 * copyright(c) 2019-2029 mamcharge.com
 */
 
package io.spring.initializr.generator.buildsystem.maven;

import java.util.ArrayList;
import java.util.List;

/**
 * //TODO 功能描述
 *
 * @author xujs@mamcharge.com
 * @date 2019/10/29 19:20
 **/
public class MavenProfile {
    private List<Property> properties;
    private boolean activeByDefault;

    public MavenProfile(Builder builder){
        this.properties = builder.properties;
        this.activeByDefault = builder.activeByDefault;
    }

    public MavenProfile(List<Property> properties, boolean activeByDefault) {
        this.properties = properties;
        this.activeByDefault = activeByDefault;
    }
    public static class Builder{
        private List<Property> properties;
        private boolean activeByDefault;
        public Builder property(Property property){
            if(properties == null){
                properties = new ArrayList<Property>();
            }
            properties.add(property);
            return this;
        }
        public Builder activeByDefault(boolean activeByDefault){
            this.activeByDefault = activeByDefault;
            return this;
        }
        public MavenProfile build(){
            return new MavenProfile(this.properties, this.activeByDefault);
        }
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public boolean isActiveByDefault() {
        return activeByDefault;
    }

    public void setActiveByDefault(boolean activeByDefault) {
        this.activeByDefault = activeByDefault;
    }

    public static class Property{
        private String name;
        private String value;
        public Property() {
        }
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
