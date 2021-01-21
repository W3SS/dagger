/*
 * Copyright (C) 2020 The Dagger Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dagger.assisted;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotates a parameter within an {@link AssistedInject}-annotated constructor.
 *
 * <p>See {@link AssistedInject}.
 */
@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface Assisted {
  /**
   * Returns an identifier for an {@link Assisted} parameter.
   *
   * <p>An assisted parameter should be uniquely defined within an {@link AssistedInject}
   * constructor by the combination of its identifier and type.
   *
   * <p>In addition, each parameter in the {@link AssistedInject} constructor must have a matching
   * parameter (identifier + type) in the associated {@link AssistedFactory} method that creates the
   * assisted type.
   *
   * <p>Example:
   *
   * <pre><code>
   * final class DataService {
   *   {@literal @}AssistedInject
   *   DataService(
   *       DataFetcher dataFetcher,
   *       {@literal @}Assisted String name,
   *       {@literal @}Assisted("id") String id,
   *       {@literal @}Assisted("repo") String repo) {}
   * }
   *
   * {@literal @}AssistedFactory
   * interface DataServiceFactory {
   *   DataService create(
   *       String name,
   *       {@literal @}Assisted("id") String id,
   *       {@literal @}Assisted("repo") String repo);
   * }
   * </code></pre>
   */
  String value() default "";
}
