package cn.ning.money.book.modules.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact, String> {

}
