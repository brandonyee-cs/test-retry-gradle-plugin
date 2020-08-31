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
package org.gradle.testretry.testframework

import org.gradle.testretry.AbstractConfigCacheFuncTest

class SpockConfigCacheFuncTest extends AbstractConfigCacheFuncTest {
    @Override
    protected String buildConfiguration() {
        return """
            dependencies {
                implementation "org.spockframework:spock-core:1.3-groovy-2.5"
                testImplementation "org.codehaus.groovy:groovy-all:2.5.8"
            }
        """
    }

    @Override
    String getLanguagePlugin() {
        return 'groovy'
    }

    @Override
    String testLanguage() {
        return 'groovy'
    }

    @Override
    String getTestAnnotation() {
        return ''
    }

    @Override
    protected void successfulTest() {
        writeTestSource """
            package acme;

            public class SuccessfulTests extends spock.lang.Specification {
                public void successTest() {
                    expect:
                    true
                }
            }
        """
    }

    @Override
    protected void flakyTest() {
        writeTestSource """
            package acme;

            public class FlakyTests extends spock.lang.Specification {
                public void flaky() {
                    expect:
                    ${flakyAssert()}
                }
            }
        """
    }
}
