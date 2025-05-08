#!/bin/bash

echo "Cloning the Test Suite"
cd /tmp
git clone -b ${TEST_BRANCH_NAME} git@gitlab.com:altairengineering/altairaccess/AccessWeb-KatalonAutomation.git
#cd access-web-ui-automation
cd AccessWeb-KatalonAutomation
chmod -R 777 *

# cp startTest.sh opt/katalonstudio/startTest.sh

echo "Updating execution profiles with access URL"
sed -i "s/lnc:4443/${TARGET_HOST_IP}:4443/g" ./Profiles/ExecProfile-adminuser.glbl
sed -i "s/lnc:4443/${TARGET_HOST_IP}:4443/g" ./Profiles/ExecProfile-david.glbl
sed -i "s/lnc:4443/${TARGET_HOST_IP}:4443/g" ./Profiles/ExecProfile-raju.glbl
sed -i "s/lnc:4443/${TARGET_HOST_IP}:4443/g" ./Profiles/ExecProfile-serviceuser.glbl

###################################################################
echo "###################################################################"
echo "Setting up PreRequisites on access instance"
echo "###################################################################"

sshpass -p "${TEST_HOST_PASSWORD}" ssh -o StrictHostKeyChecking="no" root@${TARGET_HOST_IP} 'rm -rf /stage/PreRequisites.zip; rm -rf /stage/PreRequisites ; rm -rf /stage/raju/* ; rm -rf /stage/adminuser/* ; rm -rf /stage/david/* ; rm -rf /stage/serviceuser/*'

sshpass -p "${TEST_HOST_PASSWORD}" scp -o StrictHostKeyChecking="no" /tmp/AccessWeb-KatalonAutomation/PreRequisites.zip root@${TARGET_HOST_IP}:/stage/
sshpass -p "${TEST_HOST_PASSWORD}" ssh -o StrictHostKeyChecking="no" root@${TARGET_HOST_IP} 'unzip /stage/PreRequisites.zip -d /stage/PreRequisites'

###################################################################
echo "###################################################################"
echo "Configuring App defs "
echo "###################################################################"
 
sshpass -p "${TEST_HOST_PASSWORD}" ssh -o StrictHostKeyChecking="no" root@${TARGET_HOST_IP}  'cd /stage/PreRequisites; chmod -R 777 * ; sh ./configureAppDefs.sh'

echo "###################################################################"
echo "Configuring App defs - DONE "
echo "###################################################################"
  
echo "###################################################################"
echo "Creating test data  "
echo "###################################################################"

sshpass -p "${TEST_HOST_PASSWORD}" ssh -o StrictHostKeyChecking="no" root@${TARGET_HOST_IP}  'cd /stage/PreRequisites;pwd;sh /stage/PreRequisites/createTestData.sh raju'
sshpass -p "${TEST_HOST_PASSWORD}" ssh -o StrictHostKeyChecking="no" root@${TARGET_HOST_IP}  'cd /stage/PreRequisites;pwd;sh /stage/PreRequisites/createTestData.sh david'
sshpass -p "${TEST_HOST_PASSWORD}" ssh -o StrictHostKeyChecking="no" root@${TARGET_HOST_IP}  'cd /stage/PreRequisites;pwd;sh /stage/PreRequisites/createTestData.sh adminuser'
sshpass -p "${TEST_HOST_PASSWORD}" ssh -o StrictHostKeyChecking="no" root@${TARGET_HOST_IP}  'cd /stage/PreRequisites;pwd;sh /stage/PreRequisites/createTestData.sh serviceuser'

echo "###################################################################"
echo "Creating test data - DONE  "
echo "###################################################################"

sleep 120
 
chmod 777 startTest.sh
#sh ./startTest.sh
echo "###################################################################"
echo "Running Katalon Tests from Suite Collection  : CI-CD-Set1  "
echo "###################################################################"

sh ./startTest.sh Set1
echo "###################################################################"
echo "Running Katalon Tests from Suite Collection  : CI-CD-Set2  "
echo "###################################################################"

sh ./startTest.sh Set2
echo "###################################################################"
echo "Test Runs done - Now Copying Results  "
echo "###################################################################"

mkdir -p /reports/katalon
cp -r /tmp/AccessWeb-KatalonAutomation/ExtentReports/* /reports/katalon

cp -r /tmp/AccessWeb-KatalonAutomation/Reports/* /reports/

java -jar /tmp/AccessWeb-KatalonAutomation/MergedReport.jar "/reports/katalon"

echo "###################################################################"
echo "Results Copied "
echo "###################################################################"
