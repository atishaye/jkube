/**
 * Copyright (c) 2019 Red Hat, Inc.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at:
 *
 *     https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.jkube.gradle.plugin;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jkube.gradle.plugin.task.KubernetesConfigViewTask;
import org.eclipse.jkube.gradle.plugin.task.KubernetesLogTask;
import org.eclipse.jkube.gradle.plugin.task.KubernetesResourceTask;
import org.eclipse.jkube.gradle.plugin.task.KubernetesUndeployTask;
import org.eclipse.jkube.gradle.plugin.task.OpenShiftApplyTask;
import org.eclipse.jkube.gradle.plugin.task.OpenShiftBuildTask;
import org.eclipse.jkube.gradle.plugin.task.OpenShiftResourceTask;

import org.gradle.api.Project;
import org.gradle.api.Task;

public class OpenShiftPlugin extends AbstractJKubePlugin<OpenShiftExtension> {

  public OpenShiftPlugin() {
    super("openshift", OpenShiftExtension.class);
  }

  @Override
  public Map<String, Collection<Class<? extends Task>>> getTaskPrecedence() {
    final Map<String, Collection<Class<? extends Task>>> ret = new HashMap<>();
    ret.put("ocApply", Collections.singletonList(KubernetesResourceTask.class));
    return ret;
  }

  @Override
  protected void jKubeApply(Project project) {
    register(project, "ocConfigView", KubernetesConfigViewTask.class);
    register(project, "ocBuild", OpenShiftBuildTask.class);
    register(project, "ocResource", OpenShiftResourceTask.class);
    register(project, "ocApply", OpenShiftApplyTask.class);
    register(project, "ocLog", KubernetesLogTask.class);
    register(project, "ocUndeploy", KubernetesUndeployTask.class);
  }

}
