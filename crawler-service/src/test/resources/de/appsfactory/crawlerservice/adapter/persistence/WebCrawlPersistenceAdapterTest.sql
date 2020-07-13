TRUNCATE TABLE WEB_CRAWLS;
insert into WEB_CRAWLS (ID,CRAWLED_AT, CRAWLED_PAGE_URL, DEPTH, PAGE_TITLE, SEED)
values (1, to_date('28-JUL-00','DD-MON-RR'), 'https://en.wikipedia.org/wiki/Europe', 0, 'Europe', 'https://en.wikipedia.org/wiki/Europe');
