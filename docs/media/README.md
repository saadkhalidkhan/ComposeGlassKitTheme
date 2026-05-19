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

Requires [FFmpeg](https://ffmpeg.org/). Example (~875 KB: 320px wide, 10 fps, 128-color palette):

```bash
ffmpeg -y -i docs/media/glasskit.webm \
  -vf "fps=10,scale=320:-1:flags=lanczos,split[s0][s1];[s0]palettegen=stats_mode=diff:max_colors=128[p];[s1][p]paletteuse=dither=bayer:bayer_scale=5" \
  -loop 0 docs/media/glasskit.gif
```

For an even smaller file (~400 KB), use `fps=8`, `scale=280:-1`, and `max_colors=96`.

## Optional

- `preview.svg` — legacy placeholder (not used when `glasskit.gif` is present)
