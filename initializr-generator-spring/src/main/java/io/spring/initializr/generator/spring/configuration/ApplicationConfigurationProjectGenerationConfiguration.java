/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.initializr.generator.spring.configuration;

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.initializr.metadata.InitializrMetadata;

import org.springframework.context.annotation.Bean;

/**
 * Configuration for application-related contributions to a generated project.
 *
 * @author Stephane Nicoll
 */
@ProjectGenerationConfiguration
public class ApplicationConfigurationProjectGenerationConfiguration {

//	@Bean
//	public ApplicationPropertiesContributor applicationPropertiesContributor() {
//		return new ApplicationPropertiesContributor();
//	}

	@Bean
	public ApplicationYmlContributor applicationYmlContributor(Build build, InitializrMetadata metadata){
		return new ApplicationYmlContributor(build, metadata);
	}


	@Bean
	public DockerfileContributor dockerfileContributor(){
		return new DockerfileContributor();
	}

	@Bean
	public WebFoldersContributor webFoldersContributor(Build build, InitializrMetadata metadata) {
		return new WebFoldersContributor(build, metadata);
	}

}
