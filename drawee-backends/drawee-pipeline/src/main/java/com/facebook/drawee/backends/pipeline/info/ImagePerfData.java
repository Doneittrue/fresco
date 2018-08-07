/*
 * Copyright (c) 2015-present, Facebook, Inc.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.facebook.drawee.backends.pipeline.info;

import com.facebook.common.internal.Objects;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import javax.annotation.Nullable;

public class ImagePerfData {

  public static final int UNSET = -1;

  private final @Nullable String mControllerId;
  private final @Nullable String mRequestId;
  private final @Nullable Object mCallerContext;
  private final @Nullable ImageRequest mImageRequest;
  private final @Nullable ImageInfo mImageInfo;

  private final long mControllerSubmitTimeMs;
  private final long mControllerIntermediateImageSetTimeMs;
  private final long mControllerFinalImageSetTimeMs;
  private final long mControllerFailureTimeMs;
  private final long mControllerCancelTimeMs;

  private final long mImageRequestStartTimeMs;
  private final long mImageRequestEndTimeMs;
  private final @ImageOrigin int mImageOrigin;
  private final boolean mIsPrefetch;

  private final int mOnScreenWidthPx;
  private final int mOnScreenHeightPx;

  public ImagePerfData(
      @Nullable String controllerId,
      @Nullable String requestId,
      @Nullable ImageRequest imageRequest,
      @Nullable Object callerContext,
      @Nullable ImageInfo imageInfo,
      long controllerSubmitTimeMs,
      long controllerIntermediateImageSetTimeMs,
      long controllerFinalImageSetTimeMs,
      long controllerFailureTimeMs,
      long controllerCancelTimeMs,
      long imageRequestStartTimeMs,
      long imageRequestEndTimeMs,
      @ImageOrigin int imageOrigin,
      boolean isPrefetch,
      int onScreenWidthPx,
      int onScreenHeightPx) {
    mControllerId = controllerId;
    mRequestId = requestId;
    mImageRequest = imageRequest;
    mCallerContext = callerContext;
    mImageInfo = imageInfo;
    mControllerSubmitTimeMs = controllerSubmitTimeMs;
    mControllerIntermediateImageSetTimeMs = controllerIntermediateImageSetTimeMs;
    mControllerFinalImageSetTimeMs = controllerFinalImageSetTimeMs;
    mControllerFailureTimeMs = controllerFailureTimeMs;
    mControllerCancelTimeMs = controllerCancelTimeMs;
    mImageRequestStartTimeMs = imageRequestStartTimeMs;
    mImageRequestEndTimeMs = imageRequestEndTimeMs;
    mImageOrigin = imageOrigin;
    mIsPrefetch = isPrefetch;
    mOnScreenWidthPx = onScreenWidthPx;
    mOnScreenHeightPx = onScreenHeightPx;
  }

  @Nullable
  public String getControllerId() {
    return mControllerId;
  }

  @Nullable
  public String getRequestId() {
    return mRequestId;
  }

  @Nullable
  public ImageRequest getImageRequest() {
    return mImageRequest;
  }

  @Nullable
  public Object getCallerContext() {
    return mCallerContext;
  }

  @Nullable
  public ImageInfo getImageInfo() {
    return mImageInfo;
  }

  public long getControllerSubmitTimeMs() {
    return mControllerSubmitTimeMs;
  }

  public long getControllerIntermediateImageSetTimeMs() {
    return mControllerIntermediateImageSetTimeMs;
  }

  public long getControllerFinalImageSetTimeMs() {
    return mControllerFinalImageSetTimeMs;
  }

  public long getControllerFailureTimeMs() {
    return mControllerFailureTimeMs;
  }

  public long getImageRequestStartTimeMs() {
    return mImageRequestStartTimeMs;
  }

  public long getImageRequestEndTimeMs() {
    return mImageRequestEndTimeMs;
  }

  public @ImageOrigin int getImageOrigin() {
    return mImageOrigin;
  }

  public boolean isPrefetch() {
    return mIsPrefetch;
  }

  public int getOnScreenWidthPx() {
    return mOnScreenWidthPx;
  }

  public int getOnScreenHeightPx() {
    return mOnScreenHeightPx;
  }

  public long getFinalImageLoadTimeMs() {
    if (getImageRequestEndTimeMs() == UNSET || getImageRequestStartTimeMs() == UNSET) {
      return UNSET;
    }

    return getImageRequestEndTimeMs() - getImageRequestStartTimeMs();
  }

  public long getIntermediateImageLoadTimeMs() {
    if (getControllerIntermediateImageSetTimeMs() == UNSET || getControllerSubmitTimeMs() == UNSET) {
      return UNSET;
    }

    return getControllerIntermediateImageSetTimeMs() - getControllerSubmitTimeMs();
  }

  public String createDebugString() {
    return Objects.toStringHelper(this)
        .add("controller ID", mControllerId)
        .add("request ID", mRequestId)
        .add("controller submit", mControllerSubmitTimeMs)
        .add("controller final image", mControllerFinalImageSetTimeMs)
        .add("controller failure", mControllerFailureTimeMs)
        .add("controller cancel", mControllerCancelTimeMs)
        .add("start time", mImageRequestStartTimeMs)
        .add("end time", mImageRequestEndTimeMs)
        .add("origin", ImageOriginUtils.toString(mImageOrigin))
        .add("prefetch", mIsPrefetch)
        .add("caller context", mCallerContext)
        .add("image request", mImageRequest)
        .add("image info", mImageInfo)
        .add("on-screen width", mOnScreenWidthPx)
        .add("on-screen height", mOnScreenHeightPx)
        .toString();
  }
}
