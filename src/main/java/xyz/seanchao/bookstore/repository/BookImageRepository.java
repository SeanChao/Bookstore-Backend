package xyz.seanchao.bookstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.seanchao.bookstore.entity.BookImage;

import java.util.Optional;

public interface BookImageRepository extends MongoRepository<BookImage, Integer> {
    Optional<BookImage> findByFid(int fid);
}