package com.almundo.callcenter.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.UUID;

/**
 * The Call is used to get call
 * @author <a href="guillesgc@gmail.com">Guillermo Garcia</a>
 * @version 1.0
 *
 */
public class Call {

    /**
     * Call id
     */
    private String id;

    /**
     * Message content.
     */
    private String messageContent;

    /**
     * Default constructor
     */
    public Call() {
        this.id = UUID.randomUUID().toString();
    }

    /**
     *
     * @param messageContent
     */
    public Call(String messageContent) {
        this.id = UUID.randomUUID().toString();
        this.messageContent = messageContent;
    }

    /**
     * Get call id
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Get the message content
     * @return
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * Set the call id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set the message content
     * @param messageContent
     */
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString(this);
    }
}
