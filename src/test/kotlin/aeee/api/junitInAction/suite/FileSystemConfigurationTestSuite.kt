package aeee.api.junitInAction.suite

import org.junit.platform.runner.JUnitPlatform
import org.junit.platform.suite.api.SelectPackages
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
@SelectPackages("aeee.api.junitInAction.suite")
class FileSystemConfigurationTestSuite {
}