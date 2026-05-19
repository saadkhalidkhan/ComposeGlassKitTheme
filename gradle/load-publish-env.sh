# Ensures Gpg4win is on PATH for signing.
for gpg_dir in \
  "/c/Program Files/GnuPG/bin" \
  "/c/Program Files (x86)/GnuPG/bin"
do
  if [ -x "$gpg_dir/gpg.exe" ] || [ -x "$gpg_dir/gpg" ]; then
    PATH="$gpg_dir:$PATH"
    export PATH
    break
  fi
done

# Loads Maven Central / signing entries from local.properties into ORG_GRADLE_PROJECT_*
# environment variables so Gradle sees them on the first build.
load_publish_env() {
  local file="$1"
  [ -f "$file" ] || return 0

  while IFS= read -r line || [ -n "$line" ]; do
    case "$line" in
      ''|\#*) continue ;;
      mavenCentralUsername=*) export ORG_GRADLE_PROJECT_mavenCentralUsername="${line#mavenCentralUsername=}" ;;
      mavenCentralPassword=*) export ORG_GRADLE_PROJECT_mavenCentralPassword="${line#mavenCentralPassword=}" ;;
      signing.keyId=*) export ORG_GRADLE_PROJECT_signing_keyId="${line#signing.keyId=}" ;;
      signing.password=*) export ORG_GRADLE_PROJECT_signing_password="${line#signing.password=}" ;;
      signing.secretKeyRingFile=*) export ORG_GRADLE_PROJECT_signing_secretKeyRingFile="${line#signing.secretKeyRingFile=}" ;;
    esac
  done < "$file"
}
