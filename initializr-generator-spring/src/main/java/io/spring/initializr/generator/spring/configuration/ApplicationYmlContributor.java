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
import io.spring.initializr.generator.project.contributor.ProjectContributor;
import io.spring.initializr.generator.spring.build.BuildMetadataResolver;
import io.spring.initializr.metadata.Dependency;
import io.spring.initializr.metadata.InitializrMetadata;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationYmlContributor implements ProjectContributor {

	private final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	private final Build build;
	private final BuildMetadataResolver buildMetadataResolver;

	public ApplicationYmlContributor(Build build,InitializrMetadata metadata){
		this.build = build;
		this.buildMetadataResolver = new BuildMetadataResolver(metadata);
	}

	@Override
	public void contribute(Path projectRoot) throws IOException {
		List<Dependency> mamDependencies = this.buildMetadataResolver.dependencies(build).filter((dependency)->{
			String groupId = dependency.getGroupId();
			if(groupId.startsWith("com.mamcharge")|| groupId.startsWith("com.ctrip.framework.apollo")){
				return true;
			}else{
				return false;
			}
		}).collect(Collectors.toList());
		boolean hasApollo = hasApollo(mamDependencies);
		String configurationYml = null;
		if(hasApollo){
			configurationYml = "application-apollo.yml";
		}else{
			configurationYml = "application.yml";
		}
		Path output = projectRoot.resolve("src/main/resources/application.yml");
		if (!Files.exists(output)) {
			Files.createDirectories(output.getParent());
			Files.createFile(output);
		}
		Resource resource = this.resolver.getResource("classpath:configuration/"+configurationYml);
		FileCopyUtils.copy(resource.getInputStream(), Files.newOutputStream(output, StandardOpenOption.APPEND));
	}

	private boolean hasApollo(List<Dependency> ds ){
		return hasDependecy(ds,"apollo-client");
	}

	private boolean hasDependecy(List<Dependency> ds, String artifactId){
		if(ds == null){
			return false;
		}
		for(Dependency d: ds){
			if(d.getId().indexOf(artifactId)>=0){
				return true;
			}
		}
		return false;
	}
}
