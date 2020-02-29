package th.com.woodshop.qrcode

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class CameraScanQRCode : Activity(), ZXingScannerView.ResultHandler {
    private var zXingScannerView: ZXingScannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zXingScannerView = ZXingScannerView(this)
        setContentView(zXingScannerView)
    }

    override fun onResume() {
        super.onResume()
        zXingScannerView?.setResultHandler(this)
        zXingScannerView?.startCamera()
    }

    override fun onPause() {
        super.onPause()
        zXingScannerView?.stopCamera()
    }

    override fun handleResult(result: Result?) {
        zXingScannerView?.resumeCameraPreview(this)
        val intent = Intent().apply {
            putExtra("result", result?.text)
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}