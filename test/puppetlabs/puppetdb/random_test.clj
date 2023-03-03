(ns puppetlabs.puppetdb.random-test
  (:require
   [clojure.test :refer :all]
   [puppetlabs.puppetdb.random
    :refer [random-bool
            random-node-name
            random-pp-path
            random-pronouncable-word
            random-sha1
            random-string
            random-string-alpha
            random-type-name]]))

(deftest test-random-string
  (testing "should return a string of specified length"
    (is (= 8 (count (random-string 8))))
    (is (= 30 (count (random-string 30))))
    (is (= 100 (count (random-string 100)))))

  (testing "should only accept a positive integer"
    (is (thrown? IllegalArgumentException (random-string -1)))
    (is (thrown? ClassCastException (random-string "asdf")))))

(deftest test-random-string-alpha
  (testing "should return a string of specified length"
    (is (= 8 (count (random-string-alpha 8))))
    (is (= 30 (count (random-string-alpha 30))))
    (is (= 100 (count (random-string-alpha 100)))))

  (testing "should only accept a positive integer"
    (is (thrown? IllegalArgumentException (random-string-alpha -1)))
    (is (thrown? ClassCastException (random-string-alpha "asdf")))))

(deftest test-random-bool
  (testing "should return a boolean"
    (is (boolean? (random-bool)))))

(deftest test-random-node-name
  (testing "should return a random node name"
    (is (string? (random-node-name)))
    (is (= 30 (count (random-node-name))))))

(deftest test-random-type-name
  (testing "should return a random type name"
    (is (string? (random-type-name)))
    (is (= 10 (count (random-type-name))))))

(deftest test-random-pp-path
  (testing "should return a random path"
    (is (string? (random-pp-path)))
    (is (= 54 (count (random-pp-path))))))

(deftest test-random-sha1
  (testing "returns a random sha1 hash"
    (is (string? (random-sha1)))
    (is (re-matches #"[\da-z]{40}" (random-sha1)))
    (is (re-matches #"[\da-z]{40}" (random-sha1 1000)))))

(deftest test-random-pronouncable-word
  (let [consonants "[bcdfghjklmnpqrstvwxz]"
        exp-regex-str (format "(?:%s[aeiouy])" consonants)]
    (testing "returns a random pronouncable word string"
      (is (re-matches (re-pattern (str exp-regex-str "{3}")) (random-pronouncable-word)))
      (is (re-matches (re-pattern (str exp-regex-str "{4}")) (random-pronouncable-word 8)))
      (is (re-matches (re-pattern (str exp-regex-str "{3}" consonants)) (random-pronouncable-word 7))))))
