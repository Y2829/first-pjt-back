package com.y2829.whai.common.utils;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.y2829.whai.common.exception.FailConvertException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class S3Uploader {

    private static final Logger logger = LoggerFactory.getLogger(S3Uploader.class);

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public void upload(MultipartFile multipartFile, String dirName, String fileName) {
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new FailConvertException("MultipartFile -> File로 전환이 실패했습니다."));

        upload(uploadFile, dirName, fileName);
    }

    private void upload(File uploadFile, String dirName, String fileName) {
        String filePath = dirName + "/" + fileName;
        putS3(uploadFile, filePath);
        removeNewFile(uploadFile);
    }

    private void putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(
                new PutObjectRequest(bucket, fileName, uploadFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            logger.info("파일이 삭제되었습니다.");
        } else {
            logger.info("파일이 삭제되지 못했습니다.");
        }
    }

    private Optional<File> convert(MultipartFile file) {
        File convertFile = new File(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            if (convertFile.createNewFile()) {
                try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                    fos.write(file.getBytes());
                }
            }
        } catch (IOException e) {
            throw new FailConvertException("잘못된 파일입니다.");
        }
        return Optional.of(convertFile);
    }

}
