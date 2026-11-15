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
- Post-1.0.0 work on flyme branch:
  - Close button fixed (independent top-right control).
  - Freeform sizing: clamp to physical screen, preserve orientation/aspect, defaults 90-95%.
  - Move bar is move-only; separate bottom-right resize grip.
  - Close + resize chrome auto-hides after 3s idle; reappears on move-bar touch.
  - **Back button** on freeform chrome (top-left): injects KEYCODE_BACK into the virtual display.
  - **Drag clamped** so freeform cannot be moved off the physical screen.
- Known open item from todo.md: Implement fluid gestures through accessibility so the system back key can be routed to the last-touched window (freeform vs fullscreen). Home/Recent already only affect the fullscreen task.

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
- 2026-11-15: Fixed X/close button. Root cause: top_bar_height=0dp so the button inside topBar was never visible. Added independent closeButton overlaid top-right of cardRoot in view_freeform_flyme.xml + circular background drawable. Wired in FreeformView.kt. Removed dead closeButton from view_bar.xml.
- 2026-11-15: Released alpha-11-3cd4715 (close button fix). Download: https://github.com/ckmanuel/Floating-Tab/releases/tag/alpha-11-3cd4715
- 2026-11-15: Renumbered all alpha release titles chronologically to Alpha 1 … Alpha 10 (oldest→newest). Explained why v1.0.0 appears mid-list (newer alphas published after it). Updated workflow for clearer future alpha titles.
- 2026-11-15: Freeform sizing rewrite — always fit current physical screen; keep app native orientation; raised default freeform_size 75→90 and freeform_size_land 90→95; removed portrait-on-landscape 0.55 width cap that caused overflow.
- 2026-11-15: Freeform uses 90-95% of screen by default; resize updates real LayoutParams (fixes portrait freeze); max resize 95% of screen.
- 2026-11-15: Hang-up mini window matches content orientation (landscape apps stay wide); smaller snap-to-corner threshold (0.42); freer resize 20-95%; drag resize-bar to move freeform; whole-box move in passive mode.
- 2026-11-15: Bar is move-only; separate bottom-right resize button; close+resize auto-hide after 3s, reappear when bar is touched.
- 2026-11-15 ~18:20 PST session start: Cloned repo (shallow) to /tmp, sanitized remote URL (no token), inspected latest commit b363f9a ("Separate move bar from resize; auto-hide chrome"). Restored context from context.md + todo.md. Ready to assist on next task.
- 2026-11-15 ~18:30 PST: Added freeform Back button (top-left chrome, injects KEYCODE_BACK into virtual display). Clamped drag so freeform stays fully on-screen. System Home/Recent still only affect fullscreen (desired). System Back still goes to focused fullscreen app until accessibility routing is implemented.
- 2026-11-15: Released alpha-19-6e55939 (back button + drag clamp). Download: https://github.com/ckmanuel/Floating-Tab/releases/tag/alpha-19-6e55939
