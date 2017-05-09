package musicplayer.muzio.com.muzioclient;

import musicplayer.muzio.com.muzioclient.barcodescanner.CaptureActivity;
import musicplayer.muzio.com.muzioclient.barcodescanner.DecoratedBarcodeView;

/**
 * This activity has a margin.
 */
public class SmallCaptureActivity extends CaptureActivity {
    @Override
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.capture_small);
        return (DecoratedBarcodeView)findViewById(R.id.zxing_barcode_scanner);
    }
}
