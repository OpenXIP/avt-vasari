SETLOCAL
cd ..\R\bin
set PATH=%CD%;%PATH%
cd ..
cd ..\xip-lib
set XIP_LIB_PATH=%CD%
set PATH=%XIP_LIB_PATH%;%PATH%
cd ..\Data\AD-DICOM
set AD_DICOM_STORE=%CD%
cd ..\..\Vasari
..\jdk\bin\java -Xmx512m -jar build/lib/Vasari.jar %1 %2 %3 %4
pause