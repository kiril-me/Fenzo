/*
 * Copyright 2015 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.fenzo;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class TaskRequestProvider {

    private static final AtomicInteger id = new AtomicInteger();

    static TaskRequest getTaskRequest(final double cpus, final double memory, final int ports) {
        return getTaskRequest(cpus, memory, 0.0, ports, null, null);
    }
    static TaskRequest getTaskRequest(final double cpus, final double memory, double network, final int ports) {
        return getTaskRequest(cpus, memory, network, ports, null, null);
    }
    static TaskRequest getTaskRequest(final double cpus, final double memory, final int ports,
                                      final List<? extends ConstraintEvaluator> hardConstraints,
                                      final List<? extends VMTaskFitnessCalculator> softConstraints) {
        return getTaskRequest(cpus, memory, 0.0, ports, hardConstraints, softConstraints);
    }
    static TaskRequest getTaskRequest(final double cpus, final double memory, final double network, final int ports,
                                      final List<? extends ConstraintEvaluator> hardConstraints,
                                      final List<? extends VMTaskFitnessCalculator> softConstraints) {
        final String taskId = ""+id.incrementAndGet();
        return new TaskRequest() {
            @Override
            public String getId() {
                return taskId;
            }
            @Override
            public double getCPUs() {
                return cpus;
            }
            @Override
            public double getMemory() {
                return memory;
            }
            @Override
            public double getNetworkMbps() {
                return network;
            }
            @Override
            public double getDisk() {
                return 0;
            }
            @Override
            public int getPorts() {
                return ports;
            }
            @Override
            public List<? extends ConstraintEvaluator> getHardConstraints() {
                return hardConstraints;
            }
            @Override
            public List<? extends VMTaskFitnessCalculator> getSoftConstraints() {
                return softConstraints;
            }
        };
    }
}