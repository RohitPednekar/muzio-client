package musicplayer.muzio.com.muzioclient.barcodescanner.camera;

import com.journeyapps.barcodescanner.SourceData;

/**
 * Callback for camera previews.
 */
public interface PreviewCallback {
    void onPreview(SourceData sourceData);
    void onPreviewError(Exception e);
}
