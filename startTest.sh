Suite=$1

case "$Suite" in

#################################################################################################
#################################################################################################
 "Set1")
echo "Running Set 1 "
katalonc -noSplash -runMode=console -projectPath="/tmp/AccessWeb-KatalonAutomation/AccessWeb.prj" -retry=0 -testSuiteCollectionPath="Test Suites/Sanity/CI-CD-Set1" -apiKey="bde3916c-bc58-483d-8f1a-f8f01bb4f2f4" --config -proxy.auth.option=NO_PROXY -proxy.system.option=NO_PROXY -proxy.system.applyToDesiredCapabilities=true -webui.autoUpdateDrivers=true
;;

#################################################################################################

#################################################################################################
 "Set2")
 echo "Running Set 2 "
katalonc -noSplash -runMode=console -projectPath="/tmp/AccessWeb-KatalonAutomation/AccessWeb.prj" -retry=0 -testSuiteCollectionPath="Test Suites/Sanity/CI-CD-Set2" -apiKey="bde3916c-bc58-483d-8f1a-f8f01bb4f2f4" --config -proxy.auth.option=NO_PROXY -proxy.system.option=NO_PROXY -proxy.system.applyToDesiredCapabilities=true -webui.autoUpdateDrivers=true
;;
esac