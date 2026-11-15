# Floating-Tab (Flyme-Freeform fork)

This repository is a fork of the original Mi-Freeform / Flyme freeform project.

**Goal of this fork:** Build a fully English Android app that provides floating / freeform windows. Target non-rooted devices using Shizuku (and Porter compatibility) where possible.

> Note: The upstream repository has been deprecated in favor of an Xposed-based rewrite:  
> https://github.com/relimus/Flyme-Freeform  
> That version requires Xposed and no longer needs Shizuku.

This fork keeps the Shizuku-oriented path and aims for non-root use.

## Current status
- Full English translation of documentation and user-facing strings is in progress.
- Chinese locale resources are being removed.
- Focus: reliable freeform windows via Shizuku on non-rooted Android 10+.

## License
See LICENSE.

## Open API
See [open_api.md](open_api.md) for how other apps can launch freeform windows through this app.
