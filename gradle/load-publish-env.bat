@rem Loads publish secrets from local.properties and ensures Gpg4win is on PATH for signing.
@if exist "C:\Program Files\GnuPG\bin\gpg.exe" set "PATH=C:\Program Files\GnuPG\bin;%PATH%"
@if exist "C:\Program Files (x86)\GnuPG\bin\gpg.exe" set "PATH=C:\Program Files (x86)\GnuPG\bin;%PATH%"

@rem Loads Maven Central / signing entries from local.properties into ORG_GRADLE_PROJECT_*
@rem environment variables so Gradle sees them on the first build (before gradle.properties is read).
@if "%~1"=="" exit /b 0
@if not exist "%~1" exit /b 0

for /f "usebackq eol=# tokens=1,* delims==" %%a in ("%~1") do (
  if /I "%%a"=="mavenCentralUsername" set "ORG_GRADLE_PROJECT_mavenCentralUsername=%%b"
  if /I "%%a"=="mavenCentralPassword" set "ORG_GRADLE_PROJECT_mavenCentralPassword=%%b"
  if /I "%%a"=="signing.keyId" set "ORG_GRADLE_PROJECT_signing_keyId=%%b"
  if /I "%%a"=="signing.password" set "ORG_GRADLE_PROJECT_signing_password=%%b"
  if /I "%%a"=="signing.secretKeyRingFile" set "ORG_GRADLE_PROJECT_signing_secretKeyRingFile=%%b"
)

exit /b 0
