#!/usr/bin/env bb

(require '[babashka.fs :as fs])

(def word-location (first *command-line-args*))
(def journal-location (second *command-line-args*))

(def csv-data
  (with-open [reader (io/reader word-location)]
    (doall (csv/read-csv reader))))

(def headers (->> (first csv-data)
                  (map keyword)))
(def body (rest csv-data))

(defn parse-date [created-at]
  (let [[full year month date] (-> #"^(\d{4})-(\d{2})-(\d{2})"
                                   (re-matcher created-at)
                                   re-find)]
    {:full full
     :year year
     :month month
     :date date}))

(defn property [display value]
  (when (not (empty? value)) (str display ":: " value)))

(defn parse [{:keys [word definition part-of-speach pronounciation alternative-form alternative-spelling source created-at]}]
  (let [{:keys [year month date]} (parse-date created-at)
        journal-path (str journal-location "/" year "_" month "_" date ".md")
        contents [(when (fs/exists? journal-path) (slurp journal-path))
                  (str "- " word " #word #card")
                  (property "part-of-speach" part-of-speach)
                  (property "pronounciation" pronounciation)
                  (property "alternative-spelling" alternative-spelling)
                  (property "alternative-form" alternative-form)
                  (property "source" source)
                  (str "  - " definition)]]; there might be multiple definitions, but I'm not going to worry about that because while the export used commas to separate I used commas in some of the definitions
    (fs/write-lines journal-path (remove nil? contents))))

(def values
  (->> body
       (map (partial zipmap headers))
       (map #(parse %))))

(println :finished
         (str "parsed " (count values) " words"))
