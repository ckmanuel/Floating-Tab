# Floating-Tab

Floating windows for Android — open apps in a resizable freeform overlay while you keep using the rest of your phone.

**Developer:** [ckmanuel](https://github.com/ckmanuel)  
**Latest stable:** [v1.1.0](https://github.com/ckmanuel/Floating-Tab/releases/tag/v1.1.0)

Based on the open-source Mi-Freeform / Flyme freeform work, maintained here as **Floating-Tab** with an English UI and ongoing freeform polish.

---

## Features

- **Freeform overlay** — apps run in a floating window on top of your current screen  
- **Hang-up mini window** — shrink a freeform to a corner pill; tap to restore  
- **Back / close / resize chrome** — controls on the freeform window  
- **Drag stays on screen** — freeform and hang-up cannot be dragged off the display  
- **Orientation-aware sizing** — portrait and landscape apps, portrait and landscape device  
- **Shizuku** — designed for non-root use with Shizuku (or Sui)  

## Requirements

- Android 10 or newer  
- [Shizuku](https://shizuku.rikka.app/) (or Sui) authorized for Floating-Tab  
- Overlay permission  
- Optional: accessibility or foreground service for keep-alive, depending on settings  

## Install

1. Download the latest APK from [Releases](https://github.com/ckmanuel/Floating-Tab/releases)  
2. Install and open Floating-Tab  
3. Grant Shizuku, overlay, and any other permissions the app requests  
4. Pick an app from the floating app list (or use a tile / API) to open it in freeform  

## Usage tips

| Control | Action |
|--------|--------|
| Drag the move bar | Move the freeform window |
| Resize grip (bottom-right) | Resize |
| Back (top-left) | Back inside the freeform app |
| Close (top-right) | Close the freeform |
| Hang-up | Shrink to a corner mini window |
| Fullscreen (bottom-left) | Portrait freeforms only — promote to full screen |

Landscape-native apps intentionally do not show the fullscreen control (that path was unsafe for those apps).

## Alpha builds

Pre-release builds are published as `alpha-N` on the same [Releases](https://github.com/ckmanuel/Floating-Tab/releases) page. Prefer **v1.1.0** for daily use.

## Open API

Other apps can request a freeform window through this app. See [open_api.md](open_api.md).

## License

See [LICENSE](LICENSE). Upstream Mi-Freeform / Flyme freeform projects retain their original authorship; this fork is maintained by ckmanuel as Floating-Tab.
