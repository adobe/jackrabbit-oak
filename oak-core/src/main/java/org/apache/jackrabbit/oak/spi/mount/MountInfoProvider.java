/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.jackrabbit.oak.spi.mount;

import java.util.Collection;
import java.util.Collections;

import javax.annotation.CheckForNull;

public interface MountInfoProvider {
    MountInfoProvider DEFAULT = new MountInfoProvider() {
        @Override
        public Mount getMountInfo(String path) {
            return Mount.DEFAULT;
        }

        @Override
        public Collection<Mount> getNonDefaultMounts() {
            return Collections.emptySet();
        }

        @Override
        public Mount getMount(String name) {
            return null;
        }

        @Override
        public boolean hasNonDefaultMounts() {
            return false;
        }
    };

    /**
     * Maps a given path to logical store name.
     *
     * @param path node path for which backing store location is to be determined
     * @return mountInfo for the given path. If no explicit mount configured then
     * default mount would be returned
     */
    Mount getMountInfo(String path);

    /**
     * Set of non default mount points configured for the setup
     */
    Collection<Mount> getNonDefaultMounts();

    /**
     * Returns the mount instance for given mount name
     *
     * @param name name of the mount
     * @return mount instance for given mount name. If no mount exists for given name
     * null would be returned
     */
    @CheckForNull
    Mount getMount(String name);

    /**
     * Return true if there are explicit mounts configured
     */
    boolean hasNonDefaultMounts();
}
