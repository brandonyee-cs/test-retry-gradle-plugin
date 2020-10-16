/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.testretry.internal.framework;

import org.gradle.api.internal.tasks.testing.JvmTestExecutionSpec;
import org.gradle.api.internal.tasks.testing.TestFramework;
import org.gradle.api.internal.tasks.testing.junitplatform.JUnitPlatformTestFramework;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.tasks.testing.Test;
import org.gradle.api.tasks.testing.junitplatform.JUnitPlatformOptions;
import org.gradle.internal.reflect.Instantiator;
import org.gradle.testretry.internal.TestName;

import java.util.Set;

final class Junit5TestFrameworkStrategy extends BaseJunitTestFrameworkStrategy {

    @Override
    public TestFramework createRetrying(JvmTestExecutionSpec spec, Test testTask, Set<TestName> failedTests, Instantiator instantiator, ObjectFactory objectFactory) {
        JUnitPlatformTestFramework testFramework = new JUnitPlatformTestFramework(createRetryFilter(spec, failedTests, false));
        copyTestOptions((JUnitPlatformOptions) testTask.getTestFramework().getOptions(), testFramework.getOptions());
        return testFramework;
    }

    private void copyTestOptions(JUnitPlatformOptions source, JUnitPlatformOptions target) {
        target.setIncludeEngines(source.getIncludeEngines());
        target.setExcludeEngines(source.getExcludeEngines());
        target.setIncludeTags(source.getIncludeTags());
        target.setExcludeTags(source.getExcludeTags());
    }
}
