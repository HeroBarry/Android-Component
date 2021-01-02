package com.vogue.component.adapter.mock;

import com.vogue.mock.JMockData;
import com.vogue.mock.MockConfig;

import java.util.ArrayList;
import java.util.List;

public class MockData {
    /**
     * 视频数据
     * @return
     */
    public static List<Object> generateSingleVideoData(Boolean noMore){
        MockConfig mockConfig = new MockConfig();
        mockConfig
                .subConfig(Video.class,"title")
                .stringSeed(DataUtil.getTitles())
                .sizeRange(1,1)
                .subConfig(Video.class,"cover")
                .stringSeed(DataUtil.getCovers())
                .sizeRange(1,1)
                .subConfig(Video.class,"url")
                .stringSeed(DataUtil.getVideos())
                .sizeRange(1,1)
                .subConfig(Video.class,"description")
                .stringSeed(DataUtil.getDescriptions())
                .sizeRange(1,1);

        List<Object> videos=new ArrayList<>();
        for (int i=0;i<10;i++){
            Video video=JMockData.mock(Video.class,mockConfig);
            videos.add(video);
        }
        if (noMore){
            videos.remove(0);
            videos.remove(0);
            videos.add(new NoMoreData("没有数据啦")); //加入一个NoMore对象告诉适配器显示 noMoreView
        }
        return videos;
    }
    /**
     * 卡片数据
     * @return
     */
    public static List<Object> generateTryCardData(Boolean noMore){
        MockConfig mockConfig = new MockConfig();
        mockConfig
                .subConfig(TryCard.class,"title")
                .stringSeed(DataUtil.getTitles())
                .sizeRange(1,1)
                .subConfig(TryCard.class,"cover")
                .stringSeed(DataUtil.getCovers())
                .sizeRange(1,1)
                .subConfig(TryCard.class,"description")
                .stringSeed(DataUtil.getDescriptions())
                .sizeRange(1,1);

        List<Object> tryCards=new ArrayList<>();
        for (int i=0;i<10;i++){
            TryCard tryCard= JMockData.mock(TryCard.class,mockConfig);
            tryCards.add(tryCard);
        }
        if (noMore){
            tryCards.remove(0);
            tryCards.remove(0);
            tryCards.add(new NoMoreData("没有数据啦")); //加入一个NoMore对象告诉适配器显示 noMoreView
        }
        return tryCards;
    }

    /**
     * 头条数据
     * @return
     */
    public static List<Object> generateHeadlineData(Boolean noMore){
        MockConfig mockConfig = new MockConfig();
        mockConfig
                .subConfig(Headline.class,"title")
                .stringSeed(DataUtil.getTitles())
                .sizeRange(1,1)
                .subConfig(Headline.class,"cover")
                .stringSeed(DataUtil.getCovers())
                .sizeRange(1,1)
                .subConfig(Headline.class,"url")
                .stringSeed(DataUtil.getHeadlineUrls())
                .sizeRange(1,1)
                .subConfig(Headline.class,"description")
                .stringSeed(DataUtil.getDescriptions())
                .sizeRange(1,1);

        List<Object> headlines=new ArrayList<>();
        for (int i=0;i<10;i++){
            Headline headline= JMockData.mock(Headline.class,mockConfig);
            headlines.add(headline);
        }
        if (noMore){
            headlines.remove(0);
            headlines.remove(0);
            headlines.add(new NoMoreData("没有数据啦")); //加入一个NoMore对象告诉适配器显示 noMoreView
        }
        return headlines;
    }
}
