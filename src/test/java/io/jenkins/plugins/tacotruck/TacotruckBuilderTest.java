package io.jenkins.plugins.tacotruck;

import hudson.model.FreeStyleBuild;
import hudson.model.FreeStyleProject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.junit.jupiter.WithJenkins;

@WithJenkins
class TacotruckBuilderTest {

    private static final String RUN_NAME = "Test Tacotruck Run";
    private static final String API_URL = "https://staging.api.testifesta.com";
    private static final String HANDLE = "testorg";
    private static final String PROVIDER = "provider";
    private static final String PROJECT_KEY = "project";
    private static final String CREDENTIALS_ID = "credentialsId";
    private static final String RESULTS_PATH = "./results.xml";

    private JenkinsRule jenkins;

    @BeforeEach
    void beforeEach(JenkinsRule rule) {
        jenkins = rule;
    }

    @Test
    @Disabled("Needs local installation of TacoTruck CLI")
    void testBuild() throws Exception {
        FreeStyleProject project = jenkins.createFreeStyleProject();
        TacotruckBuilder builder = new TacotruckBuilder(
                RUN_NAME, API_URL, PROVIDER, HANDLE, PROJECT_KEY, CREDENTIALS_ID, RESULTS_PATH, null);
        project.getBuildersList().add(builder);

        FreeStyleBuild build = jenkins.buildAndAssertSuccess(project);
        jenkins.assertLogContains(RUN_NAME, build);
    }
}
