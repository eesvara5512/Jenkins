#!/usr/bin/env groovy

/**
 * Fails the pipeline if project quality doesn't meet the configured quality profile (see https://docs.sonarqube.org/display/SONAR/Quality+Gates AND https://docs.sonarqube.org/display/SONAR/Quality+Profiles).
 * This DSL function must be invoked after the quality analisys has been published. Example:
 *
 * stage('sonar') { 
 *     steps {
 *         withSonarQubeEnv('sonar') { //there must be a tool named 'sonar' configured on Jenkins (/jenkins/configureTools) 
 *            sh 'mvn sonar:sonar' //publish project quality on sonar
 *        }
 *     }
 * }
 *
 * stage('quality-gates') {
 *     steps {
 *         qualityGates() //this stage will fail if project quality doesn't meet its quality profile
 *     }
 * }
 *
 */
def call() {
    timeout(time: 3, unit: 'MINUTES') {
        echo "Initializing quality gates..."
        sh 'sleep 10' //small delay because project quality can still being published on previous stage (specially on bigger projects).  
        def result = waitForQualityGate() //this is enabled by quality gates plugin: https://wiki.jenkins.io/display/JENKINS/Quality+Gates+Plugin
        if (result.status != 'OK') {
             error "Pipeline aborted due to quality gate failure: ${result.status}"
        } else {
             echo "Quality gate passed with result: ${result.status}"
        }
    }
}
