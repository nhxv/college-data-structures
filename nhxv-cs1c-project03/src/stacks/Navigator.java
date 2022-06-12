package stacks;
/**
 *
 * provide three navigation options: set current link, go back, go forward
 * @author Foothill College, Vinh Ngo
 *
 */
public class Navigator {

    private String currentLink;
    private StackList<String> backLinks;
    private StackList<String> forwardLinks;

    public Navigator() {
        this.backLinks = new StackList<>("back");
        this.forwardLinks = new StackList<>("forward");
    }


    public String getCurrentLink() {
       return this.currentLink;
    }

    public StackList<String> getBackLinks() {
        return this.backLinks;
    }

    public StackList<String> getForwardLinks() {
        return this.forwardLinks;
    }

    public void setCurrentLink(String link) {
        if (currentLink == null) {
            currentLink = link;
        } else {
            if (!currentLink.equals(link)) {
                backLinks.push(currentLink);
                currentLink = link;
                forwardLinks.clear();
            }
        }
    }

    public void setBackLinks(StackList<String> backLinks) {
        this.backLinks = backLinks;
    }

    public void setForwardLinks(StackList<String> forwardLinks) {
        this.forwardLinks = forwardLinks;
    }

    public void goBack() {
        if (!backLinks.isEmpty()) {
            forwardLinks.push(currentLink);
            currentLink = backLinks.peek();
            backLinks.pop();
        }
    }

    public void goForward() {
        if (!forwardLinks.isEmpty()) {
            backLinks.push(currentLink);
            currentLink = forwardLinks.peek();
            forwardLinks.pop();
        }
    }
}
