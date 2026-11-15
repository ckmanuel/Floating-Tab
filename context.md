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
- **v1.2.0 Stable Release** (final for now) — based on alpha-31 freeform polish.
  - Download: https://github.com/ckmanuel/Floating-Tab/releases/tag/v1.2.0
  - APK: Floating-Tab-1.2.0.apk (signed debug)
  - Highlights vs 1.1: reliable landscape freeform open/reopen, accurate touch after orientation switches, chrome buttons correctly separated (Back / Close / Resize), no fullscreen chrome.
- Prior stables: v1.1.0, v1.0.0.
- Known open item from todo.md: Implement fluid gestures through accessibility so the system back key can be routed to the last-touched window (freeform vs fullscreen).

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
- 2026-11-15: Fixed drag clamp alignment — coordinates are center-offsets (Gravity.CENTER, 0,0 = screen center), not top-left. Clamp uses live WindowMetrics bounds and ±(screen-size)/2 limits.
- 2026-11-15: Released alpha-21-c80c0b2 (drag clamp center-gravity fix). https://github.com/ckmanuel/Floating-Tab/releases/tag/alpha-21-c80c0b2
- 2026-11-15: Hang-up (mini) freeform drag also clamped on-screen via shared clampCenterOnScreen(); final snap Y clamped too.
- 2026-11-15: Released alpha-23-f7ebe3c (hang-up drag on-screen clamp). https://github.com/ckmanuel/Floating-Tab/releases/tag/alpha-23-f7ebe3c
- 2026-11-15: Landscape hang-up mini window enlarged (size from long side). Hang-up transition scale now uses freeformWidth/Height instead of rootWidth/Height to stop portrait apps squeezing on landscape devices.
- 2026-11-15: Released alpha-25-714eddc (larger landscape hang-up + portrait squeeze fix). https://github.com/ckmanuel/Floating-Tab/releases/tag/alpha-25-714eddc
- 2026-11-15: Hang-up top edge bounces (no longer destroys). Portrait hang-up enlarged. refreshForNewApp() re-detects activity orientation on every launch so alternating portrait/landscape apps do not keep the previous virtual-display mode.
- 2026-11-15: Released alpha-27-de223be (top bounce, larger portrait hang-up, orientation reset per app). https://github.com/ckmanuel/Floating-Tab/releases/tag/alpha-27-de223be
- 2026-11-15: Fixed crash opening portrait apps on landscape device — refreshForNewApp/onFreeFormRotationChanged no longer call updateViewLayout when detached; showWindow updates instead of re-addView when already attached (re-add failure path was destroying freeform).
- 2026-11-15: Released alpha-29-04efb01 (fix crash opening portrait apps on landscape). https://github.com/ckmanuel/Floating-Tab/releases/tag/alpha-29-04efb01
- 2026-11-15: Landscape-device + sensor/unspecified apps follow device orientation (landscape freeform). Hang-up restore uses freeform LayoutParams + scale=1 so chrome is not squeezed. Added bottom-left fullscreen button (moves task to default display then destroys freeform).
- 2026-11-15: Released alpha-31-d79b157 (landscape freeform, hang-up chrome, fullscreen btn). https://github.com/ckmanuel/Floating-Tab/releases/tag/alpha-31-d79b157
- 2026-11-15: Fixed landscape apps staying portrait — componentName was null at refreshForNewApp (picker only passes EXTRA_INTENT). Fixed fullscreen killing the app — use moveStack/moveRootTaskToDisplay and skip BACK inject when goingFullscreen.
- 2026-11-15: Released alpha-33-d6f71db (landscape orient + fullscreen fix). https://github.com/ckmanuel/Floating-Tab/releases/tag/alpha-33-d6f71db
- 2026-11-15: Hardened goFullscreen (moveStack + am start display 0, longer delay, never BACK). Safe mScale setters. Clamp freeform sizes to physical screen for landscape+landscape. try/catch around refreshForNewApp and onFreeFormRotationChanged.
- 2026-11-15: Released alpha-35-424f9ed (landscape/fullscreen crash hardening). https://github.com/ckmanuel/Floating-Tab/releases/tag/alpha-35-424f9ed
- 2026-11-15: Fullscreen for landscape apps — unregister task listener first; try ATM + move-stack + am task move-to-display + am start --display 0; ignore onTaskRemoval during promote; longer delay before VD teardown; improved moveStack parsing.
- 2026-11-15: Released alpha-37-4bb0758 (landscape fullscreen reparent fix). https://github.com/ckmanuel/Floating-Tab/releases/tag/alpha-37-4bb0758
- 2026-11-15: Reverted alpha-37/38 fullscreen reparent experiment (killed landscape apps). Restored alpha-35 behavior. Fullscreen chrome button hidden for landscape-native freeforms (portrait+landscape device). Stable release target: v1.1.0.
- Why two alphas per change: every push to flyme triggers Build Alpha. A follow-up "context: record alpha-N" commit was a second push → second alpha. Workflow now paths-ignore *.md so memory-only commits no longer publish builds.
- 2026-11-15: Cleaned inflated alphas 11–39. Deleted context-only + broken builds. Renumbered real builds to alpha-11 … alpha-26 (alpha-26 = v1.1.0). Workflow now uses .alpha-number sequential counter (next=27); ignores .alpha-number-only pushes.
- 2026-11-15: README rewritten for Floating-Tab (ckmanuel). App name Floating-Tab; privacy notice no longer credits KindBrave; home links point to this repo. Preparing v1.1.0 re-release with these branding fixes.
- 2026-11-15 (evening) session start: Cloned repo to /tmp (FUSE artifacts mount had I/O issues), cleaned remote URL of token, confirmed latest commit 5d708a5 (chore: bump alpha number to 28 [skip ci]). Previous meaningful: d7fde01 fix stray brace; 63eafdf v1.1 base (remove fullscreen feature). Updated Current Status for removed fullscreen. Ready for next task.
- 2026-11-15: Released alpha-29-a93ee20 (touch scale + clean landscape close/reopen). https://github.com/ckmanuel/Floating-Tab/releases/tag/alpha-29-a93ee20
- 2026-11-15: Fix landscape first-open black + second-open oversized. Root cause of oversized was moveRootTaskToDisplay(DEFAULT) on close (fullscreen task reused). Now: BACK + am task remove on close; startIntent deferred until TextureView surface is bound; hard-clamp freeformScreen/window sizes; FLAG_ACTIVITY_NEW_TASK on freeform launches.
- 2026-11-15: Released alpha-30-6fda2ab (landscape black/oversized fix). https://github.com/ckmanuel/Floating-Tab/releases/tag/alpha-30-6fda2ab
- 2026-11-15: Fix mixed chrome buttons. Close no longer injects BACK (that made X==Back). Buttons consume their own touches + bringToFront; larger hit targets. Fullscreen button remains removed (oversize snaps back to freeform).
- 2026-11-15: Released alpha-31-2f6a561 (chrome buttons unmixed). https://github.com/ckmanuel/Floating-Tab/releases/tag/alpha-31-2f6a561
- 2026-11-16: Published **v1.2.0** stable (final build for now). From alpha-31 line: landscape freeform, touch scale, clean close/reopen, chrome button roles fixed.
