# Contributing to ComposeGlassKit

Thank you for helping improve this project.

## How to contribute

1. **Search existing issues** — someone may already be working on the same idea.
2. **Open an issue** — describe the bug or feature before large changes.
3. **Fork** the repository and create a branch from `master` (or `main`).
4. **Make your changes** — keep PRs focused and reasonably small.
5. **Run checks** locally:
   ```bash
   chmod +x gradlew   # Linux/macOS if needed
   ./gradlew :glasskit:test
   ./gradlew :glasskit:assembleRelease
   ./gradlew :sample:assembleDebug
   ```
6. **Open a pull request** with:
   - A clear title and description
   - Steps to test
   - Screenshots or screen recordings for UI changes (sample app or previews)

## Code guidelines

- Follow existing Kotlin and Jetpack Compose style in `:glasskit` and `:sample`.
- Use meaningful commit messages.
- Do not commit secrets (signing keys, tokens, or private Maven credentials).
- Prefer small, reviewable commits over one large dump.
- Update [docs/API.md](docs/API.md) or [CHANGELOG.md](CHANGELOG.md) when you change public API behavior.

## Reporting bugs

Use the [bug report issue template](.github/ISSUE_TEMPLATE/bug_report.yml) and include:

- Library version (`LIBRARY_VERSION` in `gradle.properties`)
- Android API level and device
- Steps to reproduce
- Expected vs actual behavior
- Logs or screenshots if possible

## Feature requests

Use the [feature request issue template](.github/ISSUE_TEMPLATE/feature_request.yml) and explain the use case and proposed API or behavior.

## Code of conduct

This project follows the [Contributor Covenant](CODE_OF_CONDUCT.md). Be respectful and constructive.

## Questions

Open a [GitHub Issue](https://github.com/saadkhalidkhan/ComposeGlassKit/issues) and describe your question. For security concerns, see [SECURITY.md](SECURITY.md).
