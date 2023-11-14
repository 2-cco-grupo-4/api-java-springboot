package com.example.picmejava.s3;

import com.example.picmejava.infra.exception.S3UploadException;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@Component
public class S3<T> {

    private String bucket = "picme-s3";

    @Autowired
    private S3Service service;

    private String path = "imagem";

    private final Logger logger = LoggerFactory.getLogger(S3.class);


//    public byte[] getImage(@NotNull T entity, String imageUrl) {
//
//        // Bucket, Path
//        Pair<String, String> bucketParams = getBucketParams(entity, imageUrl);
//
//        logger.info("Pegando imagem do usuário no bucket: " + bucketParams.getFirst() + " e no caminho: " + bucketParams.getSecond());
//
//        return service.getObject(bucketParams.getFirst(), bucketParams.getSecond());
//    }

    public String uploadImage(MultipartFile image) {

        String imageId = UUID.randomUUID().toString();

        logger.info("Inserindo imagem do usuário no bucket: " + bucket + " e no caminho: " + path);

        try {
            service.upload(
                    bucket,
                    path,
                    image.getBytes()
            );

        } catch (IOException e) {
            throw new S3UploadException("Erro ao upload imagem: " + e.getMessage());
        }
        return imageId;
    }

//    private Pair<String, String> getBucketParams(@NotNull T entity, String imageId) {
//
//        if (entity instanceof User user) {
//            return Pair.of(bucket.getUserBucket(), path.getImagePath(user, imageId));
//        } else if (entity instanceof MeetingPoint meetingPoint) {
//            return Pair.of(bucket.getMeetingPointBucket(), path.getImagePath(meetingPoint, imageId));
//        } else if (entity instanceof Event event) {
//            return Pair.of(bucket.getEventBucket(), path.getImagePath(event, imageId));
//        } else {
//            return Pair.of(bucket.getUtilsBucket(), path.getImagePath(entity, null));
//        }
//
//    }


}
