# Media assets

| File | Used in |
|------|---------|
| `glasskit.gif` | README hero demo (autoplays on GitHub) |
| `glasskit.webm` | Source recording for regenerating the GIF |
| `theme1.png` | README preview screenshots |
| `theme2.png` | README preview screenshots |
| `theme3.png` | README preview screenshots |

## Updating assets

1. Run the `:sample` module and capture new screenshots or screen recording.
2. Replace files in this folder (keep filenames or update `README.md` paths).
3. For smaller repo size, compress PNGs or convert video to a shorter loop.

### Regenerate `glasskit.gif` from `glasskit.webm`

Requires [FFmpeg](https://ffmpeg.org/). Example (480px wide, 12 fps, palette-optimized):

```bash
ffmpeg -y -i docs/media/glasskit.webm \
  -vf "fps=12,scale=480:-1:flags=lanczos,split[s0][s1];[s0]palettegen=stats_mode=diff:max_colors=256[p];[s1][p]paletteuse=dither=bayer:bayer_scale=3" \
  -loop 0 docs/media/glasskit.gif
```

## Optional

- `preview.svg` — legacy placeholder (not used when `glasskit.gif` is present)
