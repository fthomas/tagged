resolvers += Resolver.url(
  "tpolecat-sbt-plugin-releases",
    url("http://dl.bintray.com/content/tpolecat/sbt-plugin-releases"))(
        Resolver.ivyStylePatterns)

addSbtPlugin("com.github.inthenow" % "sbt-catalysts" % "0.1.6")

addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.5.1")
