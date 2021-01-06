package com.vogue.photoselector.result;

import com.vogue.photoselector.PhotoSelector;
import com.vogue.photoselector.tools.Tools;

import java.io.File;

class BytesResult extends Result<byte[]> {

    BytesResult(PhotoSelector.ResultCallback<byte[]> callback) {
        super(callback);
    }

    @Override
    byte[] onImageResult(File file) {
        return Tools.readBytesFromFile(file);
    }

}
