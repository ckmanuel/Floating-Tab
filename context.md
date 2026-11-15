# Floating-Tab Persistent Memory / Session Context

## FIRST ACTION AT SESSION START
1. Confirm the repo is cloned and remote URL has no credentials (token must never be in .git/config or any committed file).
2. Read the latest commit message and this context.md to restore state.
3. Check todo.md and any open issues in the conversation.
4. Log the start of the new session in the Transcript section below (redact any secrets).
5. Do not duplicate memory entries — update existing ones.

## Project Goal
Android freeform / floating windows app (fork of Mi-Freeform / Flyme freeform).
- English UI, non-rooted devices via Shizuku where possible.
- Target: reliable freeform windows on Android 10+.

## Current Status (update this)
- **v1.0.0 First Stable Release** published (2026-11-15) based on commit 7ec7ca6 / former alpha-9.
  - Download: https://github.com/ckmanuel/Floating-Tab/releases/tag/v1.0.0
  - APK: Floating-Tab-1.0.0.apk (signed debug)
- Highlights in 1.0.0:
  - Landscape-native apps open with correct orientation virtual display
  - Freeform outer size always clamped to physical screen bounds (aspect preserved)
  - Close button on top-right of freeform card
- Known open item from todo.md: Implement fluid gestures through accessibility so the back key can be listened to.

## Memory Entries
(Update existing entries; do not duplicate.)

### Architecture notes
- FreeformView.kt is central for the floating window UI and behavior.
- Uses virtual displays, system APIs (some via hidden-api module), Shizuku for elevated privileges without root.
- Keep-alive modes include foreground service.

### Recent decisions
- Prefer clamping freeform size to current physical screen bounds while preserving content aspect ratio.
- Landscape content should stay landscape when phone rotates back to portrait.
- First stable release (v1.0.0) was promoted directly from the latest alpha build (alpha-9).

## Transcript (append, redact secrets)
- 2026-11-15 session start: Cloned repo, cleaned remote URL of token, inspected latest commit (7ec7ca6). Created initial context.md.
- 2026-11-15: Created tag v1.0.0 + GitHub Release "1.0.0 – First Stable Release" (non-prerelease). Uploaded Floating-Tab-1.0.0.apk (same binary as alpha-9). Release URL: https://github.com/ckmanuel/Floating-Tab/releases/tag/v1.0.0
