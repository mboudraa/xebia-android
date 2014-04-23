package fr.xebia.app.model;

import android.os.Parcel;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.xebia.app.R;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.io.File;


@RunWith(RobolectricTestRunner.class)
public class ParcelableTest {

//    PostList postList;
//    PostResponse postResponse;
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    @Before
//    public void setUp() {
//
//
//        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//
//        try {
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            postList = objectMapper.readValue(
//                    new File(classLoader.getResource("raw/postlist.json").getPath()), PostList.class);
//            postResponse = objectMapper.readValue(
//                    new File(classLoader.getResource("raw/post.json").getPath()), PostResponse.class);
//        } catch (Exception e) {
//            Assertions.fail("could not parse postlist or post", e);
//        }
//    }
//
//
////    @AutoTwip
////    public static Post posts(long id,
////                             int commentCount,
////                             String param1,
////                             String param2) {
////
////
////        return new Post(id, commentCount, new ArrayList<Comment>(), new ArrayList<Author>(), new ArrayList<Tag>(), new ArrayList<Category>(), param1, new Date(), new Date(), param1,
////                        new ArrayList<ContentItem>(), param2, param2, param2, param2, param2);
////    }
//
////    @AutoTwip
////    public static Comment comments(long id, String content, String url, String name) {
////        return new Comment(id, content, new Date(), url, name);
////    }
////
////    @AutoTwip
////    public static Author authors(long id, String description, String nickname, String slug, String name) {
////        return new Author(id, description, nickname, slug, name);
////    }
////
////    @AutoTwip
////    public static Tag tags(long id, int postCount, String description, String title) {
////        return new Tag(id, postCount, description, title);
////    }
////
//////    @AutoTwip
//////    public static ContentItem items(String type, ArrayList<Attribute> attributes, String text) {
//////        return new ContentItem(type, attributes, text);
//////    }
////
////    @AutoTwip
////    public static Category categories(long id, int postCount, String description, String title) {
////        return new Category(id, postCount, description, title);
////    }
//
//
//    @Test
//    public void shouldParcel() {
//
//        try {
//
//            Post post = postResponse.getPost();
//            System.out.println("post 1 -> " + post);
//            Parcel parcel = Parcel.obtain();
//            post.writeToParcel(parcel, 0);
//
//            parcel.setDataPosition(0);
//
//            Post deserializedPost = Post.CREATOR.createFromParcel(parcel);
//
//            System.out.println("post 2 -> " + deserializedPost);
//            Assertions.assertThat(deserializedPost).isEqualTo(post);
//        } catch (Exception e) {
//            Assertions.fail("FAILED", e);
//        }
//
//    }
}
