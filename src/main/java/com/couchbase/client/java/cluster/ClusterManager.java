/*
 * Copyright (c) 2016 Couchbase, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.couchbase.client.java.cluster;

import com.couchbase.client.core.annotations.InterfaceAudience;
import com.couchbase.client.core.annotations.InterfaceStability;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Provides management capabilities for a Couchbase Server {@link Cluster}.
 *
 * The underlying asynchronous capabilities can be leveraged through the {@link #async()} method.
 *
 * @author Michael Nitschinger
 * @since 2.0
 */
@InterfaceStability.Committed
@InterfaceAudience.Public
public interface ClusterManager {

    /**
     * Accesses the underlying {@link AsyncClusterManager} to perform asynchronous operations on the cluster.
     *
     * @return the underlying  {@link AsyncClusterManager}.
     */
    AsyncClusterManager async();

    /**
     * Provides information about the cluster with the default management timeout.
     *
     * This method throws:
     *
     * - java.util.concurrent.TimeoutException: If the timeout is exceeded.
     * - com.couchbase.client.core.CouchbaseException: If the underlying resources could not be enabled properly.
     * - com.couchbase.client.java.error.TranscodingException: If the server response could not be decoded.
     *
     * @return cluster information wrapped into a {@link ClusterInfo} object.
     */
    ClusterInfo info();

    /**
     * Provides information about the cluster with a custom timeout.
     *
     * This method throws:
     *
     * - java.util.concurrent.TimeoutException: If the timeout is exceeded.
     * - com.couchbase.client.core.CouchbaseException: If the underlying resources could not be enabled properly.
     * - com.couchbase.client.java.error.TranscodingException: If the server response could not be decoded.
     *
     * @param timeout the custom timeout.
     * @param timeUnit the time unit for the custom timeout.
     * @return cluster information wrapped into a {@link ClusterInfo} object.
     */
    ClusterInfo info(long timeout, TimeUnit timeUnit);

    /**
     * Returns a list of {@link BucketSettings} for all available {@link Bucket}s with the default management timeout.
     *
     * This method throws:
     *
     * - java.util.concurrent.TimeoutException: If the timeout is exceeded.
     * - com.couchbase.client.core.CouchbaseException: If the underlying resources could not be enabled properly.
     * - com.couchbase.client.java.error.TranscodingException: If the server response could not be decoded.
     *
     * @return a potentially empty list with {@link BucketSettings}.
     */
    List<BucketSettings> getBuckets();

    /**
     * Returns a list of {@link BucketSettings} for all available {@link Bucket}s with a custom timeout.
     *
     * This method throws:
     *
     * - java.util.concurrent.TimeoutException: If the timeout is exceeded.
     * - com.couchbase.client.core.CouchbaseException: If the underlying resources could not be enabled properly.
     * - com.couchbase.client.java.error.TranscodingException: If the server response could not be decoded.
     *
     * @param timeout the custom timeout.
     * @param timeUnit the time unit for the custom timeout.
     * @return a potentially empty list with {@link BucketSettings}.
     */
    List<BucketSettings> getBuckets(long timeout, TimeUnit timeUnit);

    /**
     * Returns the {@link BucketSettings} for the {@link Bucket} identified by name with the default management timeout.
     *
     * This method throws:
     *
     * - java.util.concurrent.TimeoutException: If the timeout is exceeded.
     * - com.couchbase.client.core.CouchbaseException: If the underlying resources could not be enabled properly.
     * - com.couchbase.client.java.error.TranscodingException: If the server response could not be decoded.
     *
     * @param name the name of the bucket.
     * @return the {@link BucketSettings} if found or null.
     */
    BucketSettings getBucket(String name);

    /**
     * Returns the {@link BucketSettings} for the {@link Bucket} identified by name with a custom timeout.
     *
     * This method throws:
     *
     * - java.util.concurrent.TimeoutException: If the timeout is exceeded.
     * - com.couchbase.client.core.CouchbaseException: If the underlying resources could not be enabled properly.
     * - com.couchbase.client.java.error.TranscodingException: If the server response could not be decoded.
     *
     * @param name the name of the bucket.
     * @param timeout the custom timeout.
     * @param timeUnit the time unit for the custom timeout.
     * @return the {@link BucketSettings} if found or null.
     */
    BucketSettings getBucket(String name, long timeout, TimeUnit timeUnit);

    /**
     * Checks if the cluster has a {@link Bucket} identified by the given name with the default management timeout.
     *
     * This method throws:
     *
     * - java.util.concurrent.TimeoutException: If the timeout is exceeded.
     * - com.couchbase.client.core.CouchbaseException: If the underlying resources could not be enabled properly.
     * - com.couchbase.client.java.error.TranscodingException: If the server response could not be decoded.
     *
     * @param name the name of the bucket.
     * @return true if it was found, false otherwise.
     */
    Boolean hasBucket(String name);

    /**
     * Checks if the cluster has a {@link Bucket} identified by the given name with a custom timeout.
     *
     * This method throws:
     *
     * - java.util.concurrent.TimeoutException: If the timeout is exceeded.
     * - com.couchbase.client.core.CouchbaseException: If the underlying resources could not be enabled properly.
     * - com.couchbase.client.java.error.TranscodingException: If the server response could not be decoded.
     *
     * @param name the name of the bucket.
     * @param timeout the custom timeout.
     * @param timeUnit the time unit for the custom timeout.
     * @return true if it was found, false otherwise.
     */
    Boolean hasBucket(String name, long timeout, TimeUnit timeUnit);

    /**
     * Inserts a {@link Bucket} with its {@link BucketSettings} if it does not already exist with the default
     * management timeout.
     *
     * This method throws:
     *
     * - java.util.concurrent.TimeoutException: If the timeout is exceeded.
     * - com.couchbase.client.core.CouchbaseException: If the underlying resources could not be enabled properly.
     * - com.couchbase.client.java.error.TranscodingException: If the server response could not be decoded.
     * - com.couchbase.client.java.error.BucketAlreadyExistsException: If the bucket already exists.
     *
     * **Note:** Inserting a Bucket is an asynchronous operation on the server side, so even if the
     * response is returned there is no guarantee that the operation has finished on the server itself.
     *
     * @param settings the bucket settings that should be applied.
     * @return the stored bucket settings if succeeded.
     */
    BucketSettings insertBucket(BucketSettings settings);

    /**
     * Inserts a {@link Bucket} with its {@link BucketSettings} if it does not already exist with a custom timeout.
     *
     * This method throws:
     *
     * - java.util.concurrent.TimeoutException: If the timeout is exceeded.
     * - com.couchbase.client.core.CouchbaseException: If the underlying resources could not be enabled properly.
     * - com.couchbase.client.java.error.TranscodingException: If the server response could not be decoded.
     * - com.couchbase.client.java.error.BucketAlreadyExistsException: If the bucket already exists.
     *
     * **Note:** Inserting a Bucket is an asynchronous operation on the server side, so even if the
     * response is returned there is no guarantee that the operation has finished on the server itself.
     *
     * @param settings the bucket settings that should be applied.
     * @return the stored bucket settings if succeeded.
     */
    BucketSettings insertBucket(BucketSettings settings, long timeout, TimeUnit timeUnit);

    /**
     * Updates a {@link Bucket} with its {@link BucketSettings} if it does already exist with the default management
     * timeout.
     *
     * This method throws:
     *
     * - java.util.concurrent.TimeoutException: If the timeout is exceeded.
     * - com.couchbase.client.core.CouchbaseException: If the underlying resources could not be enabled properly.
     * - com.couchbase.client.java.error.TranscodingException: If the server response could not be decoded.
     * - com.couchbase.client.java.error.BucketDoesNotExistException: If the bucket does not exist.
     *
     * **Note:** Updating a Bucket is an asynchronous operation on the server side, so even if the
     * response is returned there is no guarantee that the operation has finished on the server itself.
     *
     * @param settings the bucket settings that should be applied.
     * @return the updated bucket settings if succeeded.
     */
    BucketSettings updateBucket(BucketSettings settings);

    /**
     * Updates a {@link Bucket} with its {@link BucketSettings} if it does already exist with a custom timeout.
     *
     * This method throws:
     *
     * - java.util.concurrent.TimeoutException: If the timeout is exceeded.
     * - com.couchbase.client.core.CouchbaseException: If the underlying resources could not be enabled properly.
     * - com.couchbase.client.java.error.TranscodingException: If the server response could not be decoded.
     * - com.couchbase.client.java.error.BucketDoesNotExistException: If the bucket does not exist.
     *
     * **Note:** Updating a Bucket is an asynchronous operation on the server side, so even if the
     * response is returned there is no guarantee that the operation has finished on the server itself.
     *
     * @param settings the bucket settings that should be applied.
     * @param timeout the custom timeout.
     * @param timeUnit the time unit for the custom timeout.
     * @return the updated bucket settings if succeeded.
     */
    BucketSettings updateBucket(BucketSettings settings, long timeout, TimeUnit timeUnit);

    /**
     * Removes a {@link Bucket} identified by its name with the default management timeout.
     *
     * This method throws:
     *
     * - java.util.concurrent.TimeoutException: If the timeout is exceeded.
     * - com.couchbase.client.core.CouchbaseException: If the underlying resources could not be enabled properly.
     * - com.couchbase.client.java.error.TranscodingException: If the server response could not be decoded.
     *
     * **Note:** Removing a Bucket is an asynchronous operation on the server side, so even if the
     * response is returned there is no guarantee that the operation has finished on the server itself.
     *
     * @param name the name of the bucket.
     * @return true if the removal was successful, false otherwise.
     */
    Boolean removeBucket(String name);

    /**
     * Removes a {@link Bucket} identified by its name with a custom timeout.
     *
     * This method throws:
     *
     * - java.util.concurrent.TimeoutException: If the timeout is exceeded.
     * - com.couchbase.client.core.CouchbaseException: If the underlying resources could not be enabled properly.
     * - com.couchbase.client.java.error.TranscodingException: If the server response could not be decoded.
     *
     * **Note:** Removing a Bucket is an asynchronous operation on the server side, so even if the
     * response is returned there is no guarantee that the operation has finished on the server itself.
     *
     * @param name the name of the bucket.
     * @param timeout the custom timeout.
     * @param timeUnit the time unit for the custom timeout.
     * @return true if the removal was successful, false otherwise.
     */
    Boolean removeBucket(String name, long timeout, TimeUnit timeUnit);
}
