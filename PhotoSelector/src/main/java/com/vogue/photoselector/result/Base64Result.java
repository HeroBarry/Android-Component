package com.vogue.photoselector.result;

import com.vogue.photoselector.PhotoSelector;
import com.vogue.photoselector.tools.Tools;

import java.io.File;

class Base64Result extends Result<String> {

    Base64Result(PhotoSelector.ResultCallback<String> callback) {
        super(callback);
    }

    @Override
    String onImageResult(File file) {
        return Tools.readBase64FromFile(file);
    }

}
