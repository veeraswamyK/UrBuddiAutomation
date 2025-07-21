@echo off
echo ============================
echo Generating Feature File from Excel...
echo ============================

call mvn exec:java -Dexec.mainClass="utils.excelToFeatureFile"

echo ============================
echo Running Cucumber Tests...
echo ============================

call mvn clean compile
call mvn test -DsuiteXmlFile=testng.xml -Dallure.results.directory=target/allure-results

echo ============================
echo Generating Allure Report...
echo ============================

call allure generate target/allure-results --clean -o target/allure-report
call allure open target/allure-report

echo ============================
echo Test Execution Finished.
echo ============================
pause
