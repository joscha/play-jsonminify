# play-jsonminify - JSON minification compiler for Play 2.0

## About

This plugin is initially based on [Ray][] and [play-stylus][].

play-jsonminify is a [JSON][] 'compiled asset plugin' for Play 2.0 based on [JSON.simple][].
It is similar to the other built-in compilers. JSON files placed under
`app/assets` will be transformed into a pretty-printed and a compressed version.

For example, JSON in `app/assets/javascripts/xyz.json`

![](http://f.cl.ly/items/2Q2v3A1a451c3V1z1p0f/raw_json.png)

will be automatically compiled into a pretty-printed version, when requested via
`public/javascripts/xyz.json`:

![](http://f.cl.ly/items/0M29060g0u3M2v1L0Z2H/pretty_json.png)

and a compressed version when requested via `public/javascripts/xyz.min.json`:

![](http://f.cl.ly/items/3808183U3F1F0C0c243d/compressed_json.png)

Compile time errors (such as unclosed brackets or unexpected characters) will cause a nice
Play error page:

![](http://f.cl.ly/items/0V2Z0i0A0Z0y181K3j1g/play-jsonminify.png)

As you can see on line 3, leaving out commas is not necessarily breaking the compiler.
As well as unnecessary trailing commas will be stripped automatically.
(thanks to [JSON.simple][]).


## Installation

Add the plugin and the repository to your application's `project/plugins.sbt`:

    resolvers += "Play JSONminify repository" at "http://joscha.github.com/play-jsonminify/repo/"

    addSbtPlugin("com.feth" % "play-jsonminify" % "0.1.0")

This adds the JSON asset compiler to your Play project. `*.json` files beneath `app/assets` 
will then be automatically compiled to `*.json` and `*.min.json` files. Files starting with 
`_`-character will be left out from compilation as per Play convention.


## Installation (build from source)

First you must publish the plugin to your Play 2.0 repository. You
will have to specify your top level play directory and the version:

    sbt -Dplay.path=../play-2.0.1 -Dplay.version=2.0.1 publish

Then add the plugin to your application's `project/plugins.sbt`:

    addSbtPlugin("com.feth" % "play-jsonminify" % "0.1.0")

### Settings

To override the default settings, you can add the following lines to the `settings` call
in your `Build.scala` (examples):

this would for example also process JSON files starting with the `_`-character:

    jsonminifyEntryPoints <<= (sourceDirectory in Compile)(base => base / "assets" ** "*.json")

## Versions

* *0.1.0* [2012-05-18] Initial release

## License

Copyright (c) 2012 Joscha Feth

MIT-style license, see details from LICENSE file.

[Ray]: http://github.com/pufuwozu/ray
[play-stylus]: http://raw.github.com/knuton/play-stylus
[JSON]: http://www.json.org
[JSON.simple]: http://code.google.com/p/json-simple/