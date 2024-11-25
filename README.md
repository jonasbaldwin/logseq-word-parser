# Logseq Word Parser

I am finally getting in on the [Babasha](https://babashka.org/) action. This is my first script. It takes a CSV file with some of my favorite words and adds them to Journal pages in Logseq.

## Why?
Let me explain—no there is to much—let me sum up.

I am a linguaphile and I love words. I started storing my favorites in [Obsidian](https://obsidian.md) (which is the note-taking app I've been using for years), each in their own markdown file. But I recently started to use [Logseq](https://logseq.com/). I didn't, however, want to create a whole new page for each word like I had in Obsidian, but I wanted to preserve the date when I added the word (which I continently kept track of), however I didn't want to manually add _all_ of the words (70 of them) so I used a plugin to export the words and wrote this Babashka script to programmatically add them to the Journal page for the date when I added the word.

Could I have done this manually? Well, yeah, and to be honest it probably would have taken less time. But I wanted to try using a Babashka script and I absolutely don't think my time has been wasted.

## How to run this
If you have Babashka installed, you should just have to run the script `./parse.clj words.csv output/journals` from the root of this project, though you may need to run `chmod +x parse.clj` first to make is executable. `words.csv` is a list of example words and `output.journals` is a list of _heavily edited_, existing journal entries. (When I ran the script for real I specified the directory of my Logseq graph.) Of course if you do this multiple times you'll end up with duplicates, FYI.

## Will I use this again?
Possibly. As you can see if you look in the journal entries I also collect quotes and I might modify this script so I can do something similar with more quotes that I haven't added yet.

## Notes about Copyrights
There are verbatim definitions in the word list from various online dictionaries that are probably copyrighted. I don't think that I've copied enough of the respective dictionaries to violate copyright laws, and when I originally captured the words I usually sited the source for the definition though I may have missed some. There are also quotes that are attributed to various books located in the journal pages with attributions when available. Please keep this in mind if you use this code. The rest of the code is published under the MIT license. 
