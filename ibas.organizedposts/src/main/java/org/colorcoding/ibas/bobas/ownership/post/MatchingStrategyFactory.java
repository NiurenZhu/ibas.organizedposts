package org.colorcoding.ibas.bobas.ownership.post;

import org.colorcoding.ibas.bobas.ownership.post.matching.*;
import org.colorcoding.ibas.organizedposts.data.emOwnershipSign;

public class MatchingStrategyFactory {
    private volatile static MatchingStrategyFactory instance;

    public synchronized static MatchingStrategyFactory create() {
        if (instance == null) {
            synchronized (MatchingStrategyFactory.class) {
                if (instance == null) {
                    instance = new MatchingStrategyFactory();
                }
            }
        }
        return instance;
    }

    public synchronized IMatchingStrategy createMatchingStrategy(emOwnershipSign itemSign) {
        IMatchingStrategy matchingStrategy = null;
        switch (itemSign) {
            case APPROVAL:
                matchingStrategy = new ApprovalStepOwnerMatchingStrategy();
                break;
            case EQUALLEVEL:
                matchingStrategy = new ColleaguesMatchingStrategy();
                break;
            case HIGHERLEVEL:
                matchingStrategy = new LeaderMatchingStrategy();
                break;
            case LOWERLEVEL:
                matchingStrategy = new SubordinateMatchingStrategy();
                break;
            case OTHERS:
                break;
            case PROJECT:
                break;
            case SELF:
                matchingStrategy = new SelfMatchingStrategy();
                break;
            case TEAMS:
                break;
            default:
                break;
        }
        return matchingStrategy;
    }
}
