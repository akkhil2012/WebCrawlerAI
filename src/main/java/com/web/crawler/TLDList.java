package com.web.crawler;

public class TLDList {

	@SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(TLDList.class);

    private boolean onlineUpdate;

    private PublicSuffixList publicSuffixList;

    public TLDList(CrawlConfig config) throws IOException {
        this.onlineUpdate = config.isOnlineTldListUpdate();
        if (onlineUpdate) {
            InputStream stream;
            String filename = config.getPublicSuffixLocalFile();
            if (filename == null) {
                URL url = new URL(config.getPublicSuffixSourceUrl());
                stream = url.openStream();
            } else {
                stream = new FileInputStream(filename);
            }
            try {
                this.publicSuffixList = new PublicSuffixListFactory().build(stream);
            } finally {
                stream.close();
            }
        }
    }

    public boolean contains(String domain) {
        if (onlineUpdate) {
            return publicSuffixList.isPublicSuffix(domain);
        } else {
            return InternetDomainName.from(domain).isPublicSuffix();
        }
    }

    public boolean isRegisteredDomain(String domain) {
        if (onlineUpdate) {
            return publicSuffixList.isRegistrable(domain);
        } else {
            return InternetDomainName.from(domain).isTopPrivateDomain();
        }
    }
    
}
